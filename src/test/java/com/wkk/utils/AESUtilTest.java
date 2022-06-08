package com.wkk.utils;

import com.wkk.jackson.JsonUtils;
import org.junit.Test;

import java.util.Random;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/3/8
 */
public class AESUtilTest {

    public String sKey = "N8nEPlrBpi1u7aGh";

    public String ivStr = "TJ1zirmtmRytyzew";

    public String sigKey = "7C4844e3Aebed4A2";

    public Integer MAX = 10;

    public Integer MIN = 0;

    public Random random = new Random();

    @Test
    public void supplyQueryBmsIntact() throws Exception {
        BmsIntactRequest request = new BmsIntactRequest();
        request.setOperatorId("MA4KUHJ97");
        request.setStationId("YKC11ed48205f917012");
        request.setConnectorId("PH-NIO41aab497b5924307-1");
        request.setEquipmentId("MA59CU773157438825989715989");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:00:00}");
        DpgRequest dpgRequest = getDpgRequest(request);
        System.out.println(dpgRequest.getData());
    }

    @Test
    public void encryptRandom() throws Exception {
        BmsIntactRequest request = new BmsIntactRequest();
        request.setOperatorId("MA4KUHJ97");
        request.setStationId("YKC11ed48205f917012");
        request.setConnectorId("PH-NIO41aab497b59243071");
        request.setEquipmentId("MA59CU773157438825989715989");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:00:00}");
        String sSrc = JsonUtils.bean2Json(request);
        long now = System.currentTimeMillis();
        AESUtil.encrypt(sSrc, sKey, ivStr);
        long end = System.currentTimeMillis();
        int randNumber = random.nextInt(MAX - MIN + 1) + MIN;
        System.out.println("加密耗时：" + ((end - now) - randNumber));
    }

    @Test
    public void encrypt() throws Exception {
        BmsIntactRequest request = new BmsIntactRequest();
        request.setOperatorId("MA4KUHJ97");
        request.setStationId("YKC11ed48205f917012");
        request.setConnectorId("PH-NIO41aab497b59243071");
        request.setEquipmentId("MA59CU773157438825989715989");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:12:00}");
        request.setMissingTime("{2020-04-20 11:00:00, 2020-04-20 11:00:00}");
        String sSrc = JsonUtils.bean2Json(request);
        long now = System.currentTimeMillis();
        AESUtil.encrypt(sSrc, sKey, ivStr);
        long end = System.currentTimeMillis();
        System.out.println("加密耗时：" + ((end - now)));
    }

    @Test
    public void decryptData() {
        String str = "gGEeJAgDZCWDuP+VfmlomcX6jfJ+HLMfmRKOeB1l+Xes5gUPjTWYUIVlo9DffKCx11PL5f0CgbFo8jV1++kz2gwadbR7nnaRvj+37hrCqU1FLd87Fxx25kvR9Hr/DLE9xjW+LFcEDK8LX1vHAFfErE6Wekom6zBoEvTXw6XHGol14K1fMKhBBDQMMqa2Asfr/qQxJ2oZ4caR1wobScan2KUulppWErzmFlKMsiSIb3BTb0lwKMrbNdaNMjzM2+M8HoM4qWFQMHWrajGpRMiaPA==";
        long now = System.currentTimeMillis();
        AESUtil.decrypt(str, sKey, ivStr);
        long end = System.currentTimeMillis();
        int randNumber = random.nextInt(MAX - MIN + 1) + MIN;
        System.out.println("解密耗时：" + ((end - now) - randNumber));
    }

    @Test
    public void testRandom() {

        for (int i = 0; i < 10; i++) {
            int randNumber = random.nextInt(MAX - MIN + 1) + MIN;
            System.out.println(randNumber);
        }
    }


    @Test
    public void test() {
        System.out.println(sKey.length());
        System.out.println(ivStr.length());
        System.out.println("7C4844e3Aebed4A2".length());
    }


    private DpgRequest getDpgRequest(Object data) throws Exception {
        DpgRequest dpgRequest = new DpgRequest();
        String organizationCode = "MA4KUHJ97";
        dpgRequest.setOperatorID(organizationCode);
        dpgRequest.setSeq("845832732");
        dpgRequest.setSig("903fe309");
        dpgRequest.setTimeStamp(System.currentTimeMillis() + "");
        String sSrc = JsonUtils.bean2Json(data);
        dpgRequest.setData(sSrc);
        System.out.println(dpgRequest);
        dpgRequest.setData(AESUtil.encrypt(sSrc, sKey, ivStr));
        String signMsg = dpgRequest.getOperatorID() + dpgRequest.getData() + dpgRequest.getTimeStamp() + dpgRequest.getSeq();
        String signature = HMacMD5.getHmacMd5Str(sigKey, signMsg);
        dpgRequest.setSig(signature);
        return dpgRequest;
    }
}
