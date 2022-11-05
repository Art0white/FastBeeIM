package com.fastbee.fastbeeim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastbee.fastbeeim.mapper.FBApplicationMapper;
import com.fastbee.fastbeeim.pojo.FBApplication;
import com.fastbee.fastbeeim.service.IFBApplicationService;
import org.springframework.stereotype.Service;

@Service
public class FBApplicationServiceImpl extends ServiceImpl<FBApplicationMapper, FBApplication> implements IFBApplicationService {
}
