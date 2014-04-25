该项目主要介绍了spring4的几个新特性：
	1. @RESTController restful 
	   @RestController指定在控制器上，这样就不需要在每个@RequestMapping方法上加 @ResponseBody了。而且添加了一个AsyncRestTemplate ，支持REST客户端的异步无阻塞支持。