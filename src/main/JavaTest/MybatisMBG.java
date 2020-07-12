import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *   - @张浩
 * -@zhanghao2902@163.com
 *  -----0.0-------------
 */
public class MybatisMBG {

    @Test
   public void  MybatisM() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
       List<String> warnings = new ArrayList<String>();
       boolean overwrite = true;
       //指向逆向工程配置文件
       File configFile = new File("mbg1.xml");
       ConfigurationParser cp = new ConfigurationParser(warnings);
       Configuration config = cp.parseConfiguration(configFile);
       DefaultShellCallback callback = new DefaultShellCallback(overwrite);
       MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
       myBatisGenerator.generate(null);

   }
}
