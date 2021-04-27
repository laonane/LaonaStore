package wiki.laona.service;

import java.util.Map;

/**
 * @author laona
 * @description 搜索服务接口
 * @create 2021-04-24 17:54
 **/
public interface SearchService {
    /**
     * 搜索接口
     * @param paramMap 传入参数
     * @return 搜索结构
     */
    Map<String, Object> search(Map paramMap);
}
