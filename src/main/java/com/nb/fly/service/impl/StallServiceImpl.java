package com.nb.fly.service.impl;

import com.github.pagehelper.PageInfo;
import com.nb.fly.helper.PageInfoHelper;
import com.nb.fly.mapper.*;
import com.nb.fly.model.Contract;
import com.nb.fly.model.ProjectEarlyWarningModel;
import com.nb.fly.model.Stall;
import com.nb.fly.model.StallLock;
import com.nb.fly.repository.StallListVoRepository;
import com.nb.fly.repository.StallRepository;
import com.nb.fly.request.QueryStallRequest;
import com.nb.fly.response.MerchantListVO;
import com.nb.fly.response.StallListVO;
import com.nb.fly.service.StallService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fly
 * @description 档口业务实现
 * @date 2019/5/6 18:22
 */
@Slf4j
@Service
public class StallServiceImpl implements StallService {

    @Autowired
    private StallRepository stallRepository;

    @Autowired
    private StallMapper stallMapper;

    @Autowired
    private StallListVoRepository stallListVORepository;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private StallLockMapper stallLockMapper;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private PageInfoHelper pageInfoHelper;

    /**
     * 保存档口信息
     *
     * @param stall 档口信息
     */
    @Override
    public void save(Stall stall) {
        Stall saveStall = stallRepository.save(stall);
        log.info("save result -> {}", saveStall);
    }

    /**
     * 获取档口集合
     *
     * @param request 请求参数
     * @return 档口集合
     */
    @Override
    public PageInfo stallList(QueryStallRequest request) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        setParams(request, boolQueryBuilder);
        return pageInfoHelper.getPageInfo(stallListVORepository.search(boolQueryBuilder, PageRequest.of(request.getStartPage(), request.getPageSize())));
    }

    /**
     * 设置查询参数
     *
     * @param request          请求对象
     * @param boolQueryBuilder 查询对象
     */
    private void setParams(QueryStallRequest request, BoolQueryBuilder boolQueryBuilder) {
        // 模糊搜索
        if (!"".equals(request.getName()) && null != request.getName()) {
            // 分词查询
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(request.getName(), "projectName"));
        }

        // 档口等级
        if (!"".equals(request.getStallProperty()) && null != request.getStallProperty()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("stallProperty", request.getStallProperty()));
        }

        if (request.getStatus() != null) {
            // 招商  期至
            if (request.getStatus().equals(0) || request.getStatus().equals(5)) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("status", request.getStatus()));
            }

            // 我的预定
            if (request.getStatus().equals(100)) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("lockUser", request.getUserId()));
            }
            // 我的签约
            if (request.getStatus().equals(200)) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("contractUser", request.getUserId()));
            }
        }

        // 户型 houseType
        if (request.getHouseType() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("houseType", request.getHouseType()));
        }

        // 遮挡 occlusion
        if (request.getOcclusion() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("occlusion", request.getOcclusion()));
        }

        // 燃气 gas
        if (request.getGas() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("gas", request.getGas()));
        }

        // 堂食 dine
        if (request.getDine() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("dine", request.getDine()));
        }

        // 最小价格
        if (request.getMinPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent").gte(request.getMinPrice().longValue()));
        }

        // 最大价格
        if (request.getMaxPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent").lte(request.getMaxPrice().longValue()));
        }

        // 最小使用面积
        if (request.getMinArea() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("area").gte(request.getMinArea().longValue()));
        }

        // 最大使用面积
        if (request.getMaxArea() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("area").lte(request.getMaxArea().longValue()));
        }

        // 最小基础电量
        if (request.getMinElectricity() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("electricity").gte(request.getMinElectricity().longValue()));
        }

        // 最大基础电量
        if (request.getMaxElectricity() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("electricity").lte(request.getMaxElectricity().longValue()));
        }
    }

    /**
     * 修改档口信息
     *
     * @param stall 档口信息
     */
    @Override
    public void update(Stall stall) {
        Stall updateStall = stallRepository.save(stall);
        log.info("update result -> {}", updateStall);
    }

    /**
     * 删除档口响应对象
     *
     * @param stallId 档口主键
     */
    @Override
    public void deleteStallVO(Long stallId) {
        stallListVORepository.deleteById(stallId);
    }

    /**
     * 操作单个档口对象
     *
     * @param stallId 档口主键
     */
    @Override
    public void operationStall(Long stallId) {
        stallListVORepository.deleteById(stallId);
        StallListVO stallListVO = stallMapper.appletStall(stallId);
        log.info("save stall list to elasticsearch -> {}", stallListVO);
        stallListVORepository.save(stallListVO);
    }

    /**
     * 批量操作档口信息（from db to es）
     */
    @Override
    public void operationStallList() {
        stallListVORepository.deleteAll();
        // 获取全部有效的预约信息
        List<StallLock> stallLocks = stallLockMapper.allStallLock();
        // 获取全部签约信息
        List<Contract> contracts = contractMapper.allContract();
        // 获取商户信息
        List<MerchantListVO> merchantList = merchantMapper.merchantList();
        stallMapper.listAppletsStall().forEach(stallListVO -> {
            // 商户二级分类
            setSecondClassification(merchantList, stallListVO);

            // 是否是活动期
            stallListVO.setPromotions(isInActivity(stallListVO.getProjectId()));

            // 预定或已交定金
            setLockUser(stallLocks, stallListVO);

            // 签约或期至
            setContractUser(contracts, stallListVO);

            // 设置档口图片信息
            stallListVO.setStallImageList(stallMapper.querySceneGraph(stallListVO.getStallId()));
            stallListVORepository.save(stallListVO);
        });
    }

    /**
     * 设置商户二级分类
     *
     * @param merchantList 商户集合
     * @param stallListVO  档口信息
     */
    private void setSecondClassification(List<MerchantListVO> merchantList, StallListVO stallListVO) {
        for (MerchantListVO merchant : merchantList) {
            if (merchant.getId().equals(stallListVO.getMerchantId())) {
                stallListVO.setSecondClassificationName(merchant.getSecondClassification());
            }
        }
    }

    /**
     * 设置预定人
     *
     * @param stallLocks  预定信息
     * @param stallListVO 档口信息
     */
    private void setLockUser(List<StallLock> stallLocks, StallListVO stallListVO) {
        if (stallListVO.getStatus().equals((byte) 1) || stallListVO.getStatus().equals((byte) 2)) {
            // 设置预定人
            for (StallLock stallLock : stallLocks) {
                if (stallListVO.getStallId().equals(stallLock.getStallId())) {
                    stallListVO.setLockUser(stallLock.getUserId());
                }
            }
        }
    }

    /**
     * 设置签约人
     *
     * @param contracts   签约信息
     * @param stallListVO 档口信息
     */
    private void setContractUser(List<Contract> contracts, StallListVO stallListVO) {
        if (stallListVO.getStatus().equals((byte) 3) || stallListVO.getStatus().equals((byte) 4)) {
            // 设置签约人
            for (Contract contract : contracts) {
                if (stallListVO.getStallId().equals(contract.getStallId())) {
                    stallListVO.setContractUser(contract.getBusinessUserId());
                }
            }
        }
    }

    /**
     * 获取全部档口信息from DB
     *
     * @return 档口信息
     */
    @Override
    public List<StallListVO> queryStallListFromDb() {
        long startTime = System.currentTimeMillis();
        List<StallListVO> stallListVOS = stallMapper.listAppletsStall();
        long endTime = System.currentTimeMillis();
        log.info("使用DB查询全部数据需要时间 -> {}", (endTime - startTime) / 1000);
        return stallListVOS;
    }

    /**
     * 获取全部档口信息 from elasticsearch
     *
     * @return 档口信息
     */
    @Override
    public Iterable<StallListVO> queryStallListFromEs() {
        long startTime = System.currentTimeMillis();
        Iterable<StallListVO> all = stallListVORepository.findAll();
        long endTime = System.currentTimeMillis();
        log.info("使用ES查询全部数据需要时间 -> {}", (endTime - startTime) / 1000);
        return all;
    }

    /**
     * 是否活动期 0-否；1-是
     *
     * @param projectId 项目主键
     * @return 是否是或东期
     */
    private int isInActivity(Long projectId) {
        ProjectEarlyWarningModel projectEarlyWarningModel = projectMapper.findProjectEarlyWarningByProjectId(projectId);
        if (projectEarlyWarningModel == null || projectEarlyWarningModel.getExecute().equals(0)) {
            return 0;
        }
        return 1;
    }
}
