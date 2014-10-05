package org.ansj.tc;

import java.util.List;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnsjParseTest {
    private static final String strTc = "三峽河龍埔里河堤外工程施工導致河流改道，造成對岸(介壽里)土地流失";
    
	@Test
	public void ansjParseTestSc() {
		String strSc = "三峡河龙埔里河堤外工程施工导致河流改道，造成对岸(介寿里)土地流失";
		// ansj_seg-2.0.7.jar/core.dic
		// 124391	导致	65536	23548	3	{v=111, vn=1}
		String targetSc = "[三峡/ns, 河龙埔/nr, 里/f, 河堤/n, 外/f, 工程/n, 施工/vn, 导致/v, 河流/n, 改/v, 道/q, ，/w, 造成/v, 对岸/s, (, 介寿/nr, 里/f, ), 土地/n, 流失/vn]";
		List<Term> tlist = ToAnalysis.parse(strSc);
		assertEquals(targetSc, tlist.toString());    	
	}
    
    @Test
    public void ansjParseTestTcWithDic() {
    	String targetTcWithoutDic = "[三/m, 峽, 河龍埔/nr, 里/f, 河堤/n, 外/f, 工程/n, 施工/vn, 導, 致/v, 河流/n, 改/v, 道/q, ，/w, 造成/v, 對, 岸/n, (, 介/ng, 壽, 里/f, ), 土地/n, 流失/vn]";
    	List<Term> tlistWithoutDic = ToAnalysis.parse(strTc);    	
    	assertEquals(targetTcWithoutDic, tlistWithoutDic.toString());
    	
    	// [用户自定义词典路径设置 · ansjsun/ansj_seg Wiki](https://github.com/ansjsun/ansj_seg/wiki/%E7%94%A8%E6%88%B7%E8%87%AA%E5%AE%9A%E4%B9%89%E8%AF%8D%E5%85%B8%E8%B7%AF%E5%BE%84%E8%AE%BE%E7%BD%AE)
    	// loadLibrary.loadLibrary(String path)方式加载
    	UserDefineLibrary.loadLibrary(UserDefineLibrary.FOREST, "library/test/t1.dic");
    	String targetTc = "[三峽/ns, 河龍埔里/ns, 河堤/n, 外/f, 工程/n, 施工/vn, 導致/v, 河流/n, 改道/vn, ，/w, 造成/v, 對岸/s, (, 介壽里/ns, ), 土地/n, 流失/vn]";
    	//List<Term> expected = Arrays.asList(new Term());
    	List<Term> tlist = ToAnalysis.parse(strTc);
    	// [词性对照说明.中科院版本 · ansjsun/ansj_seg Wiki](https://github.com/ansjsun/ansj_seg/wiki/%E8%AF%8D%E6%80%A7%E5%AF%B9%E7%85%A7%E8%AF%B4%E6%98%8E.%E4%B8%AD%E7%A7%91%E9%99%A2%E7%89%88%E6%9C%AC)
    	// n 名词
    	// ns 地名
    	// s 处所词
    	// nr 人名
    	// f 方位词
    	// vn 名动词
    	// v 动词
    	// w 标点符号
    	assertEquals(targetTc, tlist.toString());
    }
    
}
