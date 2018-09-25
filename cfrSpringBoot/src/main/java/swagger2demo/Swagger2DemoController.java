package swagger2demo;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "swagger2demo", tags = {"swagger2demo"}) // @Api用于类上，表示对类的说明，tags属性如果声明多个，页面上会显示多个，value属性没发现影响了哪里，遗留：tags属性加了中文后，就展开不了？
public class Swagger2DemoController {
	
	@ApiOperation(value = "insert vo") // @ApiOperation用于方法上，表示对方法的作用的说明
//	@ApiImplicitParams() // @ApiImplicitParams用于方法上，表示对方法的参数的说明。表示一个隐式的请求参数，即请求方法中没有显示绑定参数名称？
	public void insert() {
		
	}
}
