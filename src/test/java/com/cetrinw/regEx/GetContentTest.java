package com.cetrinw.regEx;

import org.junit.Test;

/**
 * Created by Cetrin Wang on 2016/3/31.
 */
public class GetContentTest extends GetContent {

    @Test
    public void getContent() throws Exception {
        String html = "<div class=\"content-wrapper\"> \n" +
                "      <a target=\"_blank\" class=\"image share_url\" href=\"http://neihanshequ.com/p6203170281/\" data-group-id=\"6203170281\"> \n" +
                "       <div class=\"upload-txt  no-mb\"> \n" +
                "        <h1 class=\"title\"> <p>发小胆小，不敢对女神表白，让我帮他。于是我去表白了，女神听罢，说：你去问问他要不要你也帮他把我睡了！</p> </h1> \n" +
                "       </div> </a> \n" +
                "     </div> ";
        System.out.print(getContent(html));
    }
}