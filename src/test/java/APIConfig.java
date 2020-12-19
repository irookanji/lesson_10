import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:api.properties", "file:~/token.properties"})
public interface APIConfig extends Config {
  @DefaultValue("someUrl")
  @Key("url")
  String url();

  @DefaultValue("${token}")
  @Key("token")
  String token();
}
