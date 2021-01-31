package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @program: LaonaStore
 * @description: core包 TestCore 实现类
 * @author: HuaiAnGG
 * @create: 2021-01-31 01:14
 **/
@Service
public class TestCoreImpl implements TestCore{

    @Override
    public String getName() {
        return "This is server-seller-goods service, Dubbo ~!";
    }
}
