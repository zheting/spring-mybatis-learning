package cn.java.money.mapper;

import cn.java.money.entity.Employee;
import cn.java.money.entity.Employee2;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    /*
     * mybatis 允许增删改直接定义以下类型的返回值
     * Integer,int,Long,long, Boolean,boolean, void
     */
    Integer addEmployee(Employee employee);

    Integer updateEmployee(Employee employee);

    Integer deleteEmployee(Integer id);

    /*
     * 多个参数会被封装成一个map
     *  key: param1..paramN,或者参数的索引也可以, 新版本的mybatis是 arg0.。。argN
     *  value: 传入的参数值
     *  #{key}就是从map中获取指定的key的值
     *
     * @Param 命名参数
     */
    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /*
     * 多个参数传入对象的属性  #{属性名}取出对应参数的值
     */
    Employee getEmployeeByIdAndLastName2(Employee employee);

    /*
     * 多个参数传入的是一个Map  #{key} 取出
     */
    Employee getEmployeeByIdAndLastName3(Map map);

    /*
     * 如果多个参数经常使用，可以定义一个对象，比如分页 Page
     */

    /*
     *  Employee getEmployeeByIdAndLastName(@Param("id_") Integer id,  String lastName);
     *   取值： id = #{id_}  lastname = #{param2}
     *
     *  Employee getEmployeeByIdAndLastName(Integer id,  Employee employee);
     *  取值：id = param1    lastname = param2.lastName
     *
     *  Employee getEmployeeByIdAndLastName(Integer id,  @Param("e")Employee employee);
     *   取值：id = param1    lastname = e.lastName/param2.lastName
     *
     * 特别注意：如果是Collection(list set) 或者是数组，会封装成Map
     *      key：Collection(collection), 如果是List还可以使用这个key(list) 数组的key(array)
     * Employee getEmployeeByIdAndLastName(List<Integer> ids);
     *  取值：比如取出第五个元素 #{list[4]} 或者是 #{collection[4]}
     *
     * 总结：多个参数会封装成map,可以通过@Parma指定key,通过#{key}取值。
     *
     *      */

    /*
     * 返回List
     */
    List<Employee> getEmployeeByLastName(String lastName);

    /*
     * 返回一条记录的Map， key就是列名，值就是对应的值
     */
    Map<String,Object> getEmployeeByIdReturnMap(Integer id);

    /*
     * 返回多条记录的Map， key是主键，value就是主键对应的记录的对象
     * @MapKey("id") 告诉mybatis那一个属性作为key的值
     */
    @MapKey("id")
    Map<Integer,Employee> getEmployeeByLastNameReturnMap(String lastName);

    /*
     * 返回List， 用自定义的resultMap
     */
    List<Employee> getEmployeeByLastName2(String lastName);

    Employee2  getEmpAndDept(Integer id);

    Employee2  getEmpAndDept2(Integer id);

    List<Employee> getEmpByDepId(Integer id);

    Employee2  getEmpAndDept3(Integer id);

    List<Employee2> getEmployeeByConditionIf(Employee2 employee2);

    List<Employee2> getEmployeeByConditionTrim(Employee2 employee2);

    List<Employee2> getEmployeeByConditionChoose(Employee2 employee2);

    Integer updateEmployeeByCondation(Employee2 employee2);

    /* arg0, collection, list 或者 @Param("ids")*/
    List<Employee2> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);

    Integer saveAllEmps(List<Employee2> employee2s);

    Integer saveAllEmps2(List<Employee2> employee2s);

}
