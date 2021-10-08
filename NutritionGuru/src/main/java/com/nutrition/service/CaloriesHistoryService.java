package com.nutrition.service;

import java.util.Date;
import java.util.List;

import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;

public interface CaloriesHistoryService {
	public List<WeightLoss> getCaloriesHistoryForWeightLoss(WeightLossRequest weightLossRequest);
	public List<WeightGain> getCaloriesHistoryForWeightGain(WeightGainRequest weightGainRequest);
}
