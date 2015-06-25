package com.createidea.scrumfriend.service.impediment;

import java.util.List;

import com.createidea.scrumfriend.to.ImpedimentTo;

public interface ImpedimentService {

	public List<ImpedimentTo> getAllImpediments(String projectId);

	public ImpedimentTo saveOrUpdateImpediment(ImpedimentTo impediment);

	public ImpedimentTo saveAnalyseImpediment(ImpedimentTo impediment);

	public ImpedimentTo saveResultImpediment(ImpedimentTo impediment);

	public ImpedimentTo getImpediment(String impedimentId);

	public ImpedimentTo deleteImpediment(String impedimentId);

	public List<ImpedimentTo> filterImpediments(String filterConditions,String projectId);

	public int[][] setupSummaryOfImpediment(String projectId);

}
