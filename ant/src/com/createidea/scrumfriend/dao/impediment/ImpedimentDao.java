package com.createidea.scrumfriend.dao.impediment;

import java.util.List;

import com.createidea.scrumfriend.to.ImpedimentTo;

public interface ImpedimentDao {

	public List<ImpedimentTo> getAllImpediments(String projectId);

	public ImpedimentTo saveImpediment(ImpedimentTo impediment);

	public ImpedimentTo getImpediment(String id);

	public List<ImpedimentTo> searchImpedimentsByConditions(Integer[] filteredSatuses, Integer[] filteredseverities, String projectId);

	public int getImpedimentsCountByStatusAndSevrity(String projectId,int status, int severity);

}
