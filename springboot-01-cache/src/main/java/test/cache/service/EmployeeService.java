package test.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import test.cache.dao.EmployeeMapper;
import test.cache.entitys.Employee;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "emp")    //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存,以后再要相同的数据就直接从缓存中获取不用调用方法
     * <p>
     * CacheManager管理多个Cache组件对缓存的真正crud操作在cache组件中,每一个缓存组件有自己唯一的名字
     *
     * @Cacheable的几个属性: cacheNames/value:指定缓存的名字
     * key:缓存数据时使用的key:可以用它来指定,不指定默认是使用方法参数的值,value是方法的返回值
     * #id:参数的值    #a0 #p0 #root.args[0]
     * keyGeneration:key的生成器,可以指定key的生成器
     * key/keyGeneration二选一
     * CacheManager:指定缓存管理器/或者缓存解析器(二选一)
     * condition:指定符合条件的情况下缓存
     * unless:否定缓存,当unless指定的条件为true,方法的返回值就不会缓存,可以获取到结果进行判断
     * condition = "#id>0",unless = "#result==null"
     * sync:是否使用异步模式(若使用则不能unless)
     *
     * 原理:
     * 自动配置类:CacheAutoConfiguration
     *
     *      SimpleCacheConfiguration
     *  给容器中注册了一个CacheManager:ConcurrentMapCacheManager
     *  可以获取和创建ConcurrentMapCache类型的缓存组件,作用是将数据保存在ConcurrentMap中
     *
     *  运行流程:
     *  1.方法运行之前先去查询Cache(缓存组件),按照cacheNames指定的名字获取
     *      (CacheManager获取对应缓存),第一次获取缓存如果没有就会自动创建Cache组件
     *  2.去Cache中查找缓存内容,使用一个key,默认就是方法的参数
     *          key是按照某种策略生成的,默认是使用keyGenerator生成的(SimpleKeyGenerator)
     *          策略:
     *          没有参数:key=new SimpleKey();
     *          一个参数:key=参数的值
     *          多个参数:key=new SimpleKey(params)
     *  3.没有查到缓存就调用目标方法,然后将目标方法返回的结果放到缓存里边
     *
     *  @Cacheable标注的方法在执行之前线检查缓存中有没有这个数据,默认按照方法的参数的值作为KEY去查询缓存,
     *  如果没有就运行方法并将结果放入缓存,以后再来调用可以直接使用缓存中的数据
     *
     *  核心:使用CacheManager按照Name得到Cache(ConcurrentMap)组件
     *      key使用keyGenerator生成
     *
     */
    @Cacheable(cacheNames = {"emp"}, key = "#id", condition = "#id>0", unless = "#result==null")
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * CachePut:既调用方法,又更新缓存数据(方法一定会被调用,一定会再次查找数据库)
     * :取缓存的key和放缓存的key必须是一样的
     * 修改了数据库的某个数据,同时更新缓存
     * 运行时机:先调用目标方法,然后将目标方法结果缓存起来
     *
     * 发现一个bug:
     * mybatis的更新语句(update)默认返回int或void,而当我们要更新缓存中同一个key时,必须返回同样的类型,因此若执行update语句,
     * 可以在update之后再执行一个select语句保证返回值是同一个实体类型,
     * 这样在缓存中同一个key所存的value类型才是一样的,对同key进行缓存更新时,才不会报错.否则类型冲突
     */
    @CachePut(cacheNames = {"emp"},key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("员工更新:"+employee);
         employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * CacheEvict 缓存清除
     * 通过key指定清除的数据
     * allEntries = true:指定这个缓存中所有的数据
     * beforeInvocation = false:缓存的清除是否在方法执行之前执行
     *      默认是在方法执行之后执行,如果出现异常,缓存就不会清除
     *      = true:在返回发运行之前执行,无论是否出现异常,缓存都会清除
     */
    @CacheEvict(cacheNames = {"emp"},key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("删除员工:"+id);
        employeeMapper.deleteEmp(id);
    }

    /*
        定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByName(String lastName){
        return employeeMapper.getEmpByName(lastName);
    }
}
