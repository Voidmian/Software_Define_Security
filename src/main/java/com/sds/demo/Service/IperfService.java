package com.sds.demo.Service;

import com.sds.demo.Entity.IperfParams;

/**
 * @ClassName: IperfService
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/1  8:29
 */
public interface IperfService {
    void startIperf(IperfParams iperfParams);
    String startACommand(String iD, IperfParams iperfParams) throws Exception;
    String startCCommand(String iD);
    String get_remoteAdddress();
}
