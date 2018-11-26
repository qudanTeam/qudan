项目的package作用解释
base,config: 基本配置包
mymapper: mybatis的mapper文件，一些对应表的基本增删改查，由代码生成器自动生成，不建议修改！可联系到QQ:284646167 获取
entity: 实体类,由代码生成器生成,对应表的字段
mymappser.self: 自定义的mapper文件，一般会继承mapper包的类，这样自己写的mapper就具有了基本增删改查的能力
entity.self: 自定义的实体类
mapper: 弃用
entity: 自定义实体类
controller: 接口入口
service: 业务逻辑层
util: 工具类
