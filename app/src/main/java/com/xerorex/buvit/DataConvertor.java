package com.xerorex.buvit;

/**
 * Created by LAViATHoR on 11/29/2015.
 */

import java.util.List;

public interface DataConvertor<Input, Output> {

    public void arrayToList(Input[] array, List<Output> list);

    public void listToArray(List<Input> list, Output[] array);

    public Output convertToOutput(Input x);

    public void convertToInput(Output x, Input y);



}
