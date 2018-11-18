package cfrcollectiontodatabasedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 数据类型是List、Set、Map的话会报错，因为这几个类没有实现序列化
 * 使用ArrayList等导致可以，因为有实现序列化，但是存入的数据不是对象数据，不知道什么数据，例“ACED0005737200136A6176612E7574696C2E41727261794C6973747881D21D99C7613F000149000473697A65787000000002770400000002740004737472417400047374724278”，数据表自动生成的类型是“TINYBLOB”
 */
@SpringBootApplication
public class CollectionToDatabaseDemoApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(CollectionToDatabaseDemoApp.class);
    }
}
