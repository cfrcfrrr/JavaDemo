package usesessionpreventresubmitdemo;

// 启动后，通过localhost:8080/servlet/FormServlet生成token并跳转到/form.jsp，而不是直接去访问/form.jsp
// 遗留问题：https://www.cnblogs.com/xdp-gacl/p/3859416.html，评论的问题再看下
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 用于生成Tokent（令牌）和跳转到form.jsp页面
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = -884689940866074733L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = TokenProcessor.getInstance().makeToken();//创建令牌
        System.out.println("在FormServlet中生成的token：" + token);
        request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
        request.getRequestDispatcher("/form.jsp").forward(request, response);//跳转到form.jsp页面
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
