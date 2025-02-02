package com.dsjh.btd.service;

import com.dsjh.btd.dao.AdminDAO;
import com.dsjh.btd.dto.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService implements AdminDAO {
    private final SqlSessionTemplate sqlSession;

    public AdminService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<StaffDTO> myPageList() {
        return sqlSession.selectList("myPageList");
    }

    public List<CollegeDTO> getCollegeName(){return sqlSession.selectList("getCollegeName"); 
    }
 
    public int insertDepartment(DepartmentDTO dto){
        int res = sqlSession.insert("insertDepartment",dto);
        return res;
    }
    public List<ProfessorDTO> profList() {
        return sqlSession.selectList("profList");
    }

    public List<DepartmentDTO> departList() {
        return sqlSession.selectList("departList");
    }

    public List<ProfessorDTO> detailProf(int prof_id) {
        return sqlSession.selectList("detailProf", prof_id);
    }

    public List<SubjectDTO> subList() {
        return sqlSession.selectList("subList");
    }

    public List<ProfessorDTO> getProfName(String depart_name){
        return  sqlSession.selectList("getProfName",depart_name);
    }
    public List<ChargeInfoDTO> listSchol(){ return sqlSession.selectList("listSchol");}
    public List<ChargeInfoDTO> listTuition(){ return sqlSession.selectList("listTuition");}
    public int insertSub(SubjectDTO dto){
        int depart_id;
        int prof_id;
        String depart_name = dto.getDepart_name();
        depart_id = sqlSession.selectOne("getDepartId",depart_name);
        String prof_name = dto.getProf_name();
        prof_id = sqlSession.selectOne("getProfId",prof_name);
        dto.setDepart_id(depart_id);
        dto.setProf_id(prof_id);
        int res = sqlSession.insert("insertSub",dto);
         return res;
    }

}
