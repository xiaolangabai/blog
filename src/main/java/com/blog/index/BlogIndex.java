package com.blog.index;

import com.blog.entity.Blog;
import com.blog.utils.DateUtil;
import com.blog.utils.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * blog索引
 * @author zhousihao
 */
public class BlogIndex {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String path = null;

    private Directory dir = null;

    private Analyzer analyzer = null;

    public BlogIndex(String path){
        this.path = path;
    }

    /**
     * 构造类后请调用初始化方法，该方法加载默认配置
     */
    public void init() throws IOException {
        dir = FSDirectory.open(Paths.get(path));
        analyzer = new SmartChineseAnalyzer();
    }

    /**
     * 添加索引
     * @param blog
     * @throws IOException
     */
    public void addIndex(Blog blog) throws IOException {
        IndexWriter writer = getWriter();
        Document doc = getDocument(blog);
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 更新索引
     * @param blog
     * @throws IOException
     */
    public void updateIndex(Blog blog) throws IOException {
        IndexWriter writer = getWriter();
        Document doc = getDocument(blog);
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        writer.close();
    }

    /**
     * 删除索引
     * @param blogId
     * @throws IOException
     */
    public void deleteIndex(String blogId) throws IOException {
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term("id", blogId));
        //强制删除
        writer.forceMergeDeletes();
        writer.close();
    }

    /**
     * 博客检索
     * @param q 搜索条件
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws InvalidTokenOffsetsException
     */
    public List<Blog> searchBlog(String q) throws IOException, ParseException, InvalidTokenOffsetsException {
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

        QueryParser parser = new QueryParser("title", analyzer);
        Query titleQuery = parser.parse(q);

        QueryParser parse2 = new QueryParser("content", analyzer);
        Query contentQuery = parse2.parse(q);

        booleanQuery.add(titleQuery,BooleanClause.Occur.SHOULD);
        booleanQuery.add(contentQuery,BooleanClause.Occur.SHOULD);
        TopDocs hits = searcher.search(booleanQuery.build(), 100);

        QueryScorer scorer=new QueryScorer(titleQuery);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Blog> blogList = new LinkedList<Blog>();
        for(ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            Blog blog=new Blog();
            blog.setId(Integer.parseInt(doc.get(("id"))));
            blog.setReleaseDateStr(doc.get(("releaseDate")));
            String title=doc.get("title");
            String content = StringEscapeUtils.escapeHtml(doc.get("content"));
            if(title!=null){
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle=highlighter.getBestFragment(tokenStream, title);
                if(StringUtil.isEmpty(hTitle)){
                    blog.setTitle(title);
                }else{
                    blog.setTitle(hTitle);
                }
            }
            if(content!=null){
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent=highlighter.getBestFragment(tokenStream, content);
                if(StringUtil.isEmpty(hContent)){
                    if(content.length()<=200){
                        blog.setContent(content);
                    }else{
                        blog.setContent(content.substring(0, 200));
                    }
                }else{
                    blog.setContent(hContent);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }

    private Document getDocument(Blog blog){
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
        return doc;
    }

    private IndexWriter getWriter(){
        IndexWriterConfig iwconfig = new IndexWriterConfig(analyzer);
        IndexWriter iw = null;
        try {
            iw = new IndexWriter(dir, iwconfig);
        } catch (IOException e) {
            logger.error("获取blog索引写入器失败", e);
        }
        return iw;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setAnalyzer(Analyzer analyzer){
        this.analyzer = analyzer;
    }

    public Analyzer getAnalyzer(){
        return analyzer;
    }
}
