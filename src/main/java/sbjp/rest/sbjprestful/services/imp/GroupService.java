package sbjp.rest.sbjprestful.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sbjp.rest.sbjprestful.clientsever.request.GroupRequest;
import sbjp.rest.sbjprestful.clientsever.response.GroupReponse;
import sbjp.rest.sbjprestful.converter.GroupConverter;
import sbjp.rest.sbjprestful.entities.Group;
import sbjp.rest.sbjprestful.entities.GroupMember;
import sbjp.rest.sbjprestful.enums.TypeGroupEnum;
import sbjp.rest.sbjprestful.repositories.IGroupMemberRepository;
import sbjp.rest.sbjprestful.repositories.IGroupRepository;
import sbjp.rest.sbjprestful.repositories.IUserRepository;
import sbjp.rest.sbjprestful.services.IGroupService;


@Component
public class GroupService implements IGroupService {

	@Autowired
	private IGroupRepository groupRepository;

	@Autowired
	private IGroupMemberRepository groupMemberRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private GroupConverter groupConverter;


	@Override
	@Transactional
	public boolean add(GroupRequest request) {
		boolean check = false;

		Group groupcCheck = groupRepository.findOneByName(request.getName());

		// nếu group ko tồn tại mới tạo và thêm chủ group vào group
		if (Objects.isNull(groupcCheck)) {
			Group group = new Group();
			GroupMember groupMember = new GroupMember();
			group.setName(request.getName());
			group.setTitle(request.getTitle());
			check = groupRepository.save(group) != null ? true : false;
			// Mình sẽ thêm user đang đăng nhập vào hệ thống
			groupMember.setGroupId(group.getId());
			groupMember.setUserId(1);
			groupMember.setType(TypeGroupEnum.Type_Owner.getTypeGrpupValue());
			groupMember.setGroup(group);
			groupMember.setUser(userRepository.getById(1));
			check = groupMemberRepository.save(groupMember) != null ? true : false;
		}

		return check;
	}

	@Override
	@Transactional
	public boolean update(int idGroup,GroupRequest request) {
		boolean check = false;
		System.out.println("aaaaaaaaaaaaaa"+request.toString());
		if (Objects.nonNull(groupRepository.getById(idGroup))) {
			Group group = groupRepository.getById(idGroup);
			group.setName(request.getName());
			group.setTitle(request.getTitle());
			check = groupRepository.save(group) != null ? true : false;
		}
		return check;
	}

	@Override
	public Group findById(int id) {
		Group group =groupRepository.getById(id);
		return group;
	}

	@Override
	@Transactional
	public boolean delete(int idGroup) {
		boolean check=false;
		
		try {
			Group group=groupRepository.getById(idGroup);
			groupRepository.delete(null);
			check=true;
		} catch (Exception e) {
		}
		
		return check;
	}

	@Override
	// Nhớ lưu ý chỉ có chủ group mới được xem
	public List<GroupReponse> getAll() {
		List<GroupReponse> listGroupReponses=new ArrayList<>();
		List<Group> groups=groupRepository.findAll();
		for(Group group: groups) {
			GroupReponse groupReponse=new GroupReponse();
			groupReponse=groupConverter.converViewModel(group);
			listGroupReponses.add(groupReponse);
		}
		return listGroupReponses;
	}

}