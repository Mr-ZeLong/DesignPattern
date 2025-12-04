package com.zelon.designpattern.creational.factory;

import java.util.HashMap;
import java.util.Map;

public class RuleConfigSource {
    /*
     工厂模式的使用场景：
     1. 创建对象时逻辑复杂
     2. 需要根据配置文件动态创建不同类型的对象，存在大量的if...else或者switch...case判断
     使用工厂模式，可以将创建对象的逻辑抽离出来，放到一个工厂类中，
     让调用者只关心创建对象，而不需要知道创建的逻辑，使得调用类职责单一、结构清晰
     */
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = null;
        /*
        使用工厂模式前：
         */
        if("json".equalsIgnoreCase(ruleConfigFileExtension)){
            parser = new JsonRuleConfigParser();
        }else if("xml".equalsIgnoreCase(ruleConfigFileExtension)){
            parser = new XmlRuleConfigParser();
        }else if("properties".equalsIgnoreCase(ruleConfigFileExtension)){
            parser = new PropertiesRuleConfigParser();
        }else {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }

        // 使用工厂模式后：
        // 简单工厂模式
        parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        // 工厂方法模式
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        parser = parserFactory.createParser();

        String configText = "";
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        return parser.parse(configText);
    }

    private String getFileExtension(String ruleConfigFilePath) {
        return null;
    }
}

/*
简单工厂模式（静态工厂模式）
使用场景：当对象创建逻辑不复杂时，或者可以复用对象时
 */
// 实现方式一（对象不能复用），一般这种情况下，优先选择工厂方法
class RuleConfigParserFactory{
    public static IRuleConfigParser createParser(String configFormat){
        IRuleConfigParser parser = null;
        if("json".equalsIgnoreCase(configFormat)){
            parser = new JsonRuleConfigParser();
        }else if("xml".equalsIgnoreCase(configFormat)){
            parser = new XmlRuleConfigParser();
        }else if("properties".equalsIgnoreCase(configFormat)){
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}
// 实现方式二（对象可以复用）
class RuleConfigParserFactory2{
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();
    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }
    public static IRuleConfigParser createParser(String configFormat) throws InvalidRuleConfigException {
        if(configFormat == null || configFormat.isEmpty() || !cachedParsers.containsKey(configFormat)){
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + configFormat);
        }
        return cachedParsers.get(configFormat);
    }
}
/*
工厂方法模式
使用场景：
① 对象创建逻辑比较复杂，使用简单工厂模式到导致工厂类过于复杂；
② 对象无法复用，使用简单工厂模式需要写很多 if else
优点：面向接口编程，扩展性高，符合开闭原则
缺点：如果类特点多，就需要创建很多工厂类
 */
class RuleConfigParserFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();
    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }
    public static IRuleConfigParserFactory getParserFactory(String format) throws InvalidRuleConfigException {
        if(format == null || format.isEmpty() || !cachedFactories.containsKey(format)){
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + format);
        }
        return cachedFactories.get(format);
    }
}
interface IRuleConfigParserFactory{
    IRuleConfigParser createParser();
}

class JsonRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}

class XmlRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}




