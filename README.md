# mirai-plugin-muteme

用于 mirai-console 的禁言自己插件 高仿 _@能干辉_ 的 muteme

<a href="https://github.com/Takeoff0518/mirai-plugin-muteme/releases">
    <img src="https://img.shields.io/github/release/Takeoff0518/mirai-plugin-muteme" alt="latest version" />
  </a>

## 使用

- 在群内发送含 `muteme` 的消息，即可禁言自己随机时长（默认 15~600 秒，请修改 `config.yml`）
- 在群里发送含 `我好了` 的消息，会获得一个 30 秒的禁言

注意：机器人群内权限需要大于申请人权限

## 配置文件

位置：`./config/takeoff.muteme/config.yml`

```yaml
# 下界(s)
minTime: 15
# 上界(s)
maxTime: 600
```

-----

相关项目：[XTxiaoting14332/nonebot-plugin-muteme](https://github.com/XTxiaoting14332/nonebot-plugin-muteme/)
