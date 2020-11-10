package com.ex.util.ip;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private String country;

    private String province;

    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryShortEnName() {
        if (countries.containsKey(country)) {
            return countries.get(country);
        }
        return "Unknow";
    }

    private final static String string = "[{\"shotName\":\"AE\",\"name\":\"阿联酋\"},{\"shotName\":\"AF\",\"name\":\"阿富汗\"},{\"shotName\":\"AL\",\"name\":\"阿尔巴尼亚\"},{\"shotName\":\"AO\",\"name\":\"安哥拉\"},{\"shotName\":\"AR\",\"name\":\"阿根廷\"},{\"shotName\":\"AT\",\"name\":\"奥地利\"},{\"shotName\":\"AU\",\"name\":\"澳大利亚\"},{\"shotName\":\"AZ\",\"name\":\"阿塞拜疆\"},{\"shotName\":\"BD\",\"name\":\"孟加拉\"},{\"shotName\":\"BE\",\"name\":\"比利时\"},{\"shotName\":\"BG\",\"name\":\"保加利亚\"},{\"shotName\":\"BH\",\"name\":\"巴林\"},{\"shotName\":\"BI\",\"name\":\"布隆迪\"},{\"shotName\":\"BJ\",\"name\":\"贝宁\"},{\"shotName\":\"BM\",\"name\":\"百慕大\"},{\"shotName\":\"BN\",\"name\":\"文莱\"},{\"shotName\":\"BO\",\"name\":\"玻利维亚\"},{\"shotName\":\"BR\",\"name\":\"巴西\"},{\"shotName\":\"BS\",\"name\":\"巴哈马\"},{\"shotName\":\"BT\",\"name\":\"不丹\"},{\"shotName\":\"BW\",\"name\":\"博茨瓦纳\"},{\"shotName\":\"CA\",\"name\":\"加拿大\"},{\"shotName\":\"CF\",\"name\":\"中非共和国\"},{\"shotName\":\"CG\",\"name\":\"刚果\"},{\"shotName\":\"CH\",\"name\":\"瑞士\"},{\"shotName\":\"CK\",\"name\":\"库克群岛\"},{\"shotName\":\"CL\",\"name\":\"智利\"},{\"shotName\":\"CM\",\"name\":\"喀麦隆\"},{\"shotName\":\"CN\",\"name\":\"中国\"},{\"shotName\":\"CO\",\"name\":\"哥伦比亚\"},{\"shotName\":\"CR\",\"name\":\"哥斯达黎加\"},{\"shotName\":\"CU\",\"name\":\"古巴\"},{\"shotName\":\"CV\",\"name\":\"佛得角群岛\"},{\"shotName\":\"CY\",\"name\":\"塞浦路斯\"},{\"shotName\":\"CZ\",\"name\":\"捷克共和国\"},{\"shotName\":\"DE\",\"name\":\"德国\"},{\"shotName\":\"DK\",\"name\":\"丹麦\"},{\"shotName\":\"DZ\",\"name\":\"阿尔及利亚\"},{\"shotName\":\"EC\",\"name\":\"厄瓜多尔\"},{\"shotName\":\"EE\",\"name\":\"爱沙尼亚\"},{\"shotName\":\"EG\",\"name\":\"埃及\"},{\"shotName\":\"ES\",\"name\":\"西班牙\"},{\"shotName\":\"ET\",\"name\":\"埃塞俄比亚\"},{\"shotName\":\"FI\",\"name\":\"芬兰\"},{\"shotName\":\"FJ\",\"name\":\"斐济\"},{\"shotName\":\"FR\",\"name\":\"法国\"},{\"shotName\":\"GA\",\"name\":\"加蓬\"},{\"shotName\":\"GB\",\"name\":\"英国\"},{\"shotName\":\"GD\",\"name\":\"格林纳达\"},{\"shotName\":\"GH\",\"name\":\"加纳\"},{\"shotName\":\"GM\",\"name\":\"冈比亚\"},{\"shotName\":\"GN\",\"name\":\"几内亚\"},{\"shotName\":\"GQ\",\"name\":\"赤道几内亚\"},{\"shotName\":\"GR\",\"name\":\"希腊\"},{\"shotName\":\"GT\",\"name\":\"危地马拉\"},{\"shotName\":\"GU\",\"name\":\"关岛\"},{\"shotName\":\"GY\",\"name\":\"圭亚那\"},{\"shotName\":\"HK\",\"name\":\"香港\"},{\"shotName\":\"HN\",\"name\":\"洪都拉斯\"},{\"shotName\":\"HR\",\"name\":\"克罗地亚\"},{\"shotName\":\"HT\",\"name\":\"海地\"},{\"shotName\":\"HU\",\"name\":\"匈牙利\"},{\"shotName\":\"ID\",\"name\":\"印度尼西亚\"},{\"shotName\":\"IE\",\"name\":\"爱尔兰\"},{\"shotName\":\"IL\",\"name\":\"以色列\"},{\"shotName\":\"IN\",\"name\":\"印度\"},{\"shotName\":\"IQ\",\"name\":\"伊拉克\"},{\"shotName\":\"IR\",\"name\":\"伊朗\"},{\"shotName\":\"IS\",\"name\":\"冰岛\"},{\"shotName\":\"IT\",\"name\":\"意大利\"},{\"shotName\":\"JM\",\"name\":\"牙买加\"},{\"shotName\":\"JO\",\"name\":\"约旦\"},{\"shotName\":\"JP\",\"name\":\"日本\"},{\"shotName\":\"KE\",\"name\":\"肯尼亚\"},{\"shotName\":\"KH\",\"name\":\"柬埔寨\"},{\"shotName\":\"KP\",\"name\":\"韩国\"},{\"shotName\":\"KR\",\"name\":\"北朝鲜\"},{\"shotName\":\"KW\",\"name\":\"科威特\"},{\"shotName\":\"KZ\",\"name\":\"哈萨克斯坦\"},{\"shotName\":\"LA\",\"name\":\"老挝\"},{\"shotName\":\"LB\",\"name\":\"黎巴嫩\"},{\"shotName\":\"LT\",\"name\":\"立陶宛\"},{\"shotName\":\"LU\",\"name\":\"卢森堡\"},{\"shotName\":\"LV\",\"name\":\"拉托维亚\"},{\"shotName\":\"LY\",\"name\":\"利比亚\"},{\"shotName\":\"MA\",\"name\":\"摩洛哥\"},{\"shotName\":\"MC\",\"name\":\"摩纳哥\"},{\"shotName\":\"MD\",\"name\":\"摩尔多瓦\"},{\"shotName\":\"MG\",\"name\":\"马达加斯加\"},{\"shotName\":\"ML\",\"name\":\"马里\"},{\"shotName\":\"MN\",\"name\":\"蒙古\"},{\"shotName\":\"MO\",\"name\":\"澳门\"},{\"shotName\":\"MR\",\"name\":\"毛里塔尼亚\"},{\"shotName\":\"MT\",\"name\":\"马耳他\"},{\"shotName\":\"MU\",\"name\":\"毛里求斯\"},{\"shotName\":\"MV\",\"name\":\"马尔代夫\"},{\"shotName\":\"MX\",\"name\":\"墨西哥\"},{\"shotName\":\"MY\",\"name\":\"马来西亚\"},{\"shotName\":\"MZ\",\"name\":\"莫桑比克\"},{\"shotName\":\"NA\",\"name\":\"纳米比亚\"},{\"shotName\":\"NE\",\"name\":\"尼日尔\"},{\"shotName\":\"NG\",\"name\":\"尼日利亚\"},{\"shotName\":\"NI\",\"name\":\"尼加拉瓜\"},{\"shotName\":\"NL\",\"name\":\"荷兰\"},{\"shotName\":\"NO\",\"name\":\"挪威\"},{\"shotName\":\"NP\",\"name\":\"尼泊尔\"},{\"shotName\":\"NZ\",\"name\":\"新西兰\"},{\"shotName\":\"OM\",\"name\":\"阿曼\"},{\"shotName\":\"PA\",\"name\":\"巴拿马\"},{\"shotName\":\"PE\",\"name\":\"秘鲁\"},{\"shotName\":\"PG\",\"name\":\"巴布亚新几内亚\"},{\"shotName\":\"PH\",\"name\":\"菲律宾\"},{\"shotName\":\"PK\",\"name\":\"巴基斯坦\"},{\"shotName\":\"PL\",\"name\":\"波兰\"},{\"shotName\":\"PT\",\"name\":\"葡萄牙\"},{\"shotName\":\"PY\",\"name\":\"巴拉圭\"},{\"shotName\":\"QA\",\"name\":\"卡塔尔\"},{\"shotName\":\"RO\",\"name\":\"罗马尼亚\"},{\"shotName\":\"RU\",\"name\":\"俄罗斯\"},{\"shotName\":\"RW\",\"name\":\"卢旺达\"},{\"shotName\":\"SA\",\"name\":\"沙特阿拉伯\"},{\"shotName\":\"SD\",\"name\":\"苏丹\"},{\"shotName\":\"SE\",\"name\":\"瑞典\"},{\"shotName\":\"SG\",\"name\":\"新加坡\"},{\"shotName\":\"SK\",\"name\":\"斯洛伐克\"},{\"shotName\":\"SM\",\"name\":\"圣马力诺\"},{\"shotName\":\"SN\",\"name\":\"塞内加尔\"},{\"shotName\":\"SO\",\"name\":\"索马里\"},{\"shotName\":\"SY\",\"name\":\"叙利亚\"},{\"shotName\":\"TH\",\"name\":\"泰国\"},{\"shotName\":\"TJ\",\"name\":\"塔吉克斯坦\"},{\"shotName\":\"TM\",\"name\":\"土库曼斯坦\"},{\"shotName\":\"TN\",\"name\":\"突尼斯\"},{\"shotName\":\"TO\",\"name\":\"汤加\"},{\"shotName\":\"TW\",\"name\":\"台湾\"},{\"shotName\":\"TZ\",\"name\":\"坦桑尼亚\"},{\"shotName\":\"UA\",\"name\":\"乌克兰\"},{\"shotName\":\"UG\",\"name\":\"乌干达\"},{\"shotName\":\"UK\",\"name\":\"英国\"},{\"shotName\":\"US\",\"name\":\"美国\"},{\"shotName\":\"UY\",\"name\":\"乌拉圭\"},{\"shotName\":\"UZ\",\"name\":\"乌兹别克斯坦\"},{\"shotName\":\"VA\",\"name\":\"梵蒂冈\"},{\"shotName\":\"VE\",\"name\":\"委内瑞拉\"},{\"shotName\":\"VN\",\"name\":\"越南\"},{\"shotName\":\"YE\",\"name\":\"也门\"},{\"shotName\":\"YU\",\"name\":\"南斯拉夫\"},{\"shotName\":\"ZA\",\"name\":\"南非\"},{\"shotName\":\"ZM\",\"name\":\"赞比亚\"},{\"shotName\":\"ZR\",\"name\":\"扎伊尔\"},{\"shotName\":\"ZW\",\"name\":\"津巴布韦\"}]";
    private static Map<String, String> countries;

    static {
        JSONArray array = JSONArray.parseArray(string);
        countries = new HashMap<String, String>();
        for (Object object : array) {
            JSONObject jo = (JSONObject) object;
            countries.put(jo.getString("name"), jo.getString("shotName"));
        }
    }
}
