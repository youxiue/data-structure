package webservice.publish;

import webservice.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class UserDao {

    public void add(){
        System.out.println("add");
    }
    public void update(){
        System.out.println("update");
    }

    /**
     * webMethod -> exclude=true 为不发布该接口
     */
    @WebMethod(exclude = true)
    public void sayHello(){
        System.out.println("hello");
    }

    public User getUserById(int id){
        User user = new User();
        user.setId(id);
        user.setName("香辣兰花豆");
        user.setAge("3");
        user.setEmail("youxiue@qq.com");
        return user;
    }

}
