package com.sk.mbc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
  첫번째 강의
 */

// browser -> spring container -> @Controller -> Model 생성
@Controller
public class HelloController {

    @GetMapping("hello")   // 호출이 http://localhost:8080/hello 매핑해준다
    public String hello(Model model) {   // model은 스프링컨테이너가 생성해서 넘겨준다.
        model.addAttribute("data", "hello222!!");  // model에 data=hello222!! 저장해라
        return ("hello"); // hello 라는 템플릿을 찾아라
    }

    @GetMapping("hello-mvc")   // 호출이 http://localhost:8080/hello-mvc?name="spring" 매핑해준다
    public String helloMvc(@RequestParam("name") String name, Model model) {   // model은 스프링컨테이너가 생성해서 넘겨준다.
        model.addAttribute("name", name);  // model에 name=(name 값-브라우저에서 전송한 값) 저장해라
        System.out.println("name:" + name);
        return ("hello-template"); // hello-template 라는 템플릿을 찾아라
    }

    @GetMapping("hello-string")
    @ResponseBody   //이것의 의미는 직접 reponse html body에 하위의 "hello" + name까지 직접 넣어서 리턴하겠다는 의미
    public String helloString(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("name:" + name);
        return "hello " + name;
    }

    @GetMapping("hello-html")
    @ResponseBody   //이것의 의미는 직접 reponse html body에 하위의 <html>~</html>까지 직접 넣어서 리턴하겠다는 의미
    public String helloHtml(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("name:" + name);
        return "<html> hello " + name + "</html>";
    }

    // ======================================= API
    // 객체를 가지고 조자작해보

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name, Model model) {
        Hello hello = new Hello();

        try {
            hello.setName(name);
            hello.setData(null);
            //hello.setData(""); //isEmpty
        } catch (Exception e) {
            e.printStackTrace();
            hello.setData("null");
        }

        return hello;  //hello라는 객체를 반환한다. 그럼 브라우저에서는 어떻게 반응할까 // {"name":"tttttttttttttt"} json으로 온다.
    }

    // Hello라는 클래스를 내부에 정의
    static class Hello {
        private String name;
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            if (data.isEmpty()){
                data = "isEmpty";
            }
            else if(data.isBlank()){
                data="isBlank";
            }
            else{
                data="몰라";
            }
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {

            this.name = name;
        }
    }


}
