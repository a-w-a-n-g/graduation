package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.ModifyTopicBiz;
import com.graduation.spring.biz.TopicBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月1日 下午6:11:00
* 
*/

@Service("modifyTopicBiz")
public class ModifyTopicBizImpl implements ModifyTopicBiz {

	@Resource(name="handleEntityDAO")
	private HandleEntityDAO handle;
	
	@Resource
	private TopicBiz topicBiz;
	
	public ModifyTopicBizImpl() {
		
	}

	@PostConstruct
	public void init() {
		handle.setType("ModifyTopic");		
	}
	
	@Override
	public ArrayList<ModifyTopic> getAllModifyTopic() {
		return (ArrayList<ModifyTopic>)handle.setData(new ModifyTopic()).setBaseQuery().setOrder("modifyTime", "desc").getResultList();
	}

	@Override
	public ArrayList<ModifyTopic> likeSearch(String selection) {
		ArrayList<ModifyTopic> returnList = new ArrayList<ModifyTopic>();
		ArrayList<ModifyTopic> subList;
		subList = (ArrayList<ModifyTopic>)handle.setData(new ModifyTopic()).setBaseQuery().setLikeQuery("title", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<ModifyTopic>)handle.setBaseQuery().setLikeQuery("difficulty", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		return returnList;
	}
	
	@Override
	public boolean insertModify(ModifyTopic mt) {
		return handle.setData(mt).insertEntity();
	}
	
	@Override
	public ArrayList<ModifyTopic> getAdminCheckTopic() {
		ModifyTopic mt = new ModifyTopic();
		mt.setIfPass_tch(true);
		return (ArrayList<ModifyTopic>)handle.setData(mt).setBaseQuery().setOrder("modifyTime", "desc").getResultList();
	}
	
	@Override
	public boolean adminAgreeModify(Integer id, String opinion) {
		ModifyTopic data = new ModifyTopic();
		data.setId(id);
		data = (ModifyTopic)handle.setData(data).setBaseQuery().getResultList().get(0);
		Topic topic = topicBiz.getTopicById(data.getTopicId());
		if(!data.getTitle().equals(topic.getTitle())) {
			topic.setTitle(data.getTitle());
		}
		if(!data.getDepartmentName().equals(topic.getDepartmentName())) {
			topic.setDepartmentName(data.getDepartmentName());
		}
		if(!data.getDifficulty().equals(topic.getDifficulty())) {
			topic.setDifficulty(data.getDifficulty());
		}
		if(!data.getIntroduction().equals(topic.getIntroduction())) {
			topic.setIntroduction(data.getIntroduction());
		}
		if(!data.getRequirement().equals(topic.getRequirement())) {
			topic.setRequirement(data.getRequirement());
		}
		data.setIfPass_admin(true);
		data.setOpinion(opinion);
		boolean b = handle.setData(data).updateEntity() && topicBiz.updateTopic(topic);
		return b;
	}
	
	@Override
	public boolean adminDisagreeModify(Integer id, String opinion) {
		ModifyTopic data = new ModifyTopic();
		data.setId(id);
		data = (ModifyTopic)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setIfPass_admin(false);
		data.setOpinion(opinion);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean tchAgreeModify(Integer id, String opinion) {
		ModifyTopic data = new ModifyTopic();
		data.setId(id);
		data = (ModifyTopic)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setIfPass_tch(true);
		data.setOpinion(opinion);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean tchDisagreeModify(Integer id, String opinion) {
		ModifyTopic data = new ModifyTopic();
		data.setId(id);
		data = (ModifyTopic)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setIfPass_tch(false);
		data.setOpinion(opinion);
		return handle.setData(data).updateEntity();
	}

}
