package com.yw.bos.service;

import com.yw.bos.domain.Decidedzone;
import com.yw.bos.utils.PageBean;

public interface IDecidedzoneService {

    void save(Decidedzone model, String[] subareaid);

    void pageQuery(PageBean pageBean);
}
