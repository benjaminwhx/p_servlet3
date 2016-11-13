package com.jd.jr.chapter5.bigpipe;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:33
 */
public interface PageletViewResolver {

    /**
     * 暂时没考虑国际化
     * @param pageletResult
     */
    PageletView resolve(PageletResult pageletResult);
}
