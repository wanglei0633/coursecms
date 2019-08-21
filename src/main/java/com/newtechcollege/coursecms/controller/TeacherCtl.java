package com.newtechcollege.coursecms.controller;

import com.alibaba.fastjson.JSONObject;
import com.newtechcollege.coursecms.entity.Teacher;
import com.newtechcollege.coursecms.service.TeacherService;
import com.newtechcollege.coursecms.util.QiniuUtil;
import com.newtechcollege.coursecms.util.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * 
 * 教师控制层api
  * @return : null
 * @author wanglei
 * @date 2019/8/16 9:39
 */

@RestController
@CrossOrigin(origins = "*")
@Validated
@RequestMapping(value = "/teacher")
public class TeacherCtl {

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师列表接口
     */
    @RequestMapping(value = "/select")
    public List selectAll(){
        List<Teacher> list = teacherService.selectTeacherAll();
        return list;
    }
    /**
     * 教师详情接口
     */
    @RequestMapping(value = "/details")
    public String teacherById(@NotNull(message = "teacherid不能为空！") Integer teacherid){
            Teacher teacher = teacherService.selectTeacherById(teacherid);
            return RestfulUtil.success(teacher);
    }
    /**
     * 教师姓名模糊查询
     */
    @RequestMapping(value = "/like")
    public List teacherLike(String likename){
        List<Teacher> list = teacherService.selectTeacherLike(likename);
        return list;
        }
        /**
     *新增教师
     */

    @RequestMapping(value = "/insert")
    public String insertTeacher(@Valid Teacher teacher)  {
//        String teachername = teacher.getTeachername();
//        String teacherpro = teacher.getTeachername();
//        String teacherimgsrc = teacher.getTeacherimgsrc();
//        String teacherdetail = teacher.getTeacherdetail();
//        if ("".equals(teachername)){
//            RestfulUtil.http(1,null,"teachername不能为空");
//        }
//        if ("".equals(teacherpro)){
//            RestfulUtil.http(1,null,"teacherpro不能为空");
//        }
//        if ("".equals(teacherimgsrc)){
//            RestfulUtil.http(1,null,"teacherimgsrc不能为空");
//        }
//        if ("".equals(teacherdetail)){
//            RestfulUtil.http(1,null,"teacherdetail不能为空");
//        }



        return  "";
//        JSONObject json = new JSONObject();
//        System.out.println("12312312"+map);
       /* System.out.println(teachername);
        Map<String, String> map = new HashMap<>();
        map.put("teachername",teachername);
        map.put("teacherpro",teacherpro);*/
        //json.put("data",teachername);
//        return map.toString();
    }
    /**
     * 删除教师
     */
    @RequestMapping(value = "/delete")
    public int deleteTeacher(@NotNull(message = "teacherid不能为空") Integer teacherid){
        return teacherService.deleteTeacherById(teacherid);
    }
    /**
     * 教师头像上传
     */
    @RequestMapping(value = "/teacherUpload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String pathUrl = QiniuUtil.uploadImg(inputStream, UUID.randomUUID().toString().substring(0, 8));
        return pathUrl;
    }
    /**
     * 修改教师是否展示
     */
    @RequestMapping(value = "/updateTeacherStatus")
    public int status(@NotNull(message = "teacherid不能为空") Integer teacherid,@NotNull(message = "status不能为空") Integer status){
        return teacherService.updataTeacherStatusById(teacherid,status);
    }
    /**
     * 修改教师信息
     */
    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
    public String updata(@NotNull(message = "teacherid不能为空") Integer teacherid, @Valid Teacher teacher, BindingResult bindingResult){
       if (bindingResult.hasErrors()){
           return RestfulUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
       }else {
           Integer integer = teacherService.updataTeacher(teacherid, teacher);
           return RestfulUtil.success(integer);
       }
    }
       /**
     * 修改教师头像
     */
    @RequestMapping(value = "/updateTeacherImg",method = RequestMethod.POST)
    public int updataImg(@NotNull(message = "teacherid不能为空") Integer teacherid,@NotBlank(message = "teacherimgsrc不能为空") String teacherimgsrc){
        return teacherService.updataTeacherImg(teacherid,teacherimgsrc);
    }
}
