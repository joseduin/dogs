package joseduin.dogbreeds;

import org.junit.Test;

import static org.junit.Assert.*;
import joseduin.dogbreeds.util.StringUtil;

public class StringUtilUnitTest {

    @Test
    public void toLowerCase() throws Exception {
        String s = "HeLloWoRd";
        assertEquals("helloword", StringUtil.toLowerCase(s));
    }

    @Test
    public void contains() throws Exception {
        String s1 = "My name is Jose Duin";
        String s2 = "some";
        assertEquals(false, StringUtil.contains(s1, s2));

        s1 = "My name is Jose Duin";
        s2 = "jose duin";
        assertEquals(true, StringUtil.contains(s1, s2));

        s1 = "My name is Jose Duin";
        s2 = "  JOSE duin    ";
        assertEquals(true, StringUtil.contains(s1, s2));
    }

    @Test
    public void nullTranform() throws Exception {
        String s = null;
        assertEquals("", StringUtil.nullTranform(s));

        s = "";
        assertEquals("", StringUtil.nullTranform(s));

        s = "hi";
        assertEquals("hi", StringUtil.nullTranform(s));
    }


}
