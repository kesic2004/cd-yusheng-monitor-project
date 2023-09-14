package com.model.license.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.license.entity.License;
import com.model.license.mapper.LicenseMapper;
import com.model.license.service.LicenseService;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl extends ServiceImpl<LicenseMapper, License> implements LicenseService {
}
