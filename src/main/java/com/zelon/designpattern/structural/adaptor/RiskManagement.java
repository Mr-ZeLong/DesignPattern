package com.zelon.designpattern.structural.adaptor;

import java.util.ArrayList;
import java.util.List;

// 未使用适配器模式之前的代码：代码的可测试性、扩展性不好
public class RiskManagement {
    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
    private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();

    public String filterSensitiveWords1(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        maskedText = bFilter.filter(maskedText);
        maskedText = cFilter.filter(maskedText, "***");
        return maskedText;
    }
}

// 没有使用适配器改造之前的代码
class ASensitiveWordsFilter { // A敏感词过滤系统提供的接口
    //text是原始文本，函数输出用***替换敏感词之后的文本
    public String filterSexyWords(String text) {
        // ...
        return "";
    }

    public String filterPoliticalWords(String text) {
        // ...
        return "";
    }
}

class BSensitiveWordsFilter  { // B敏感词过滤系统提供的接口
    public String filter(String text) {
        //...
        return "";
    }
}

class CSensitiveWordsFilter { // C敏感词过滤系统提供的接口
    public String filter(String text, String mask) {
        //...
        return "";
    }
}


// 使用适配器改造后
// 扩展性更好，更加符合开闭原则，如果添加一个新的敏感词过滤系统，
// 这个类完全不需要改动；而且基于接口而非实现编程，代码的可测试性更好。
class RiskManagement2 {
    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    public String filterSensitiveWords(String text) {
        String maskedText = text;
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}

interface ISensitiveWordsFilter{
    String filter(String text);
}

class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter){
        this.aFilter = aFilter;
    }

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterPoliticalWords(text);
        maskedText = aFilter.filterSexyWords(maskedText);
        return maskedText;
    }
}

class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
    public BSensitiveWordsFilterAdaptor(BSensitiveWordsFilter bFilter){
        this.bFilter = bFilter;
    }

    @Override
    public String filter(String text) {
        return bFilter.filter(text);
    }
}
class CSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();
    public CSensitiveWordsFilterAdaptor(CSensitiveWordsFilter cFilter){
        this.cFilter = cFilter;
    }

    @Override
    public String filter(String text) {
        return cFilter.filter(text, "***");
    }
}
