import org.junit.Test;
import pojo.SysUser;

public class TestLombok {
    @Test
    public void testAnnotation(){
        SysUser sysUser = new SysUser(1, "", "");
        System.out.println(sysUser);
    }
}
