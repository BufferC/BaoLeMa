package com.fc.dto;

import com.fc.entity.SetMeal;
import com.fc.entity.SetMealDish;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SetMealDto extends SetMeal {

    private List<SetMealDish> setmealDishes;

    private String categoryName;
}
