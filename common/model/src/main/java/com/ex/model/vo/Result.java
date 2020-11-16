package com.ex.model.vo;


import com.ex.model.enums.ResultEnum;

public class Result {


    /**
     * 默认返回数据
     * @return Result模型的数据
     */
    public static ResultVO success(Object object) {
        ResultVO resultvo = new ResultVO();
        resultvo.setData(object);
        resultvo.setCode(200);
        resultvo.setMsg("success");
        return resultvo;
    }

    /**
     * 返回数据,并定义状态码
     * @return Result模型的数据
     */
    public static ResultVO success(Object object, int status) {
        ResultVO resultvo = new ResultVO();
        resultvo.setData(object);
        resultvo.setCode(status);
        resultvo.setMsg("success");
        return resultvo;
    }


    /**
     * 返回数据,提示状态码和提示语
     * @return Result模型的数据
     */
    public static ResultVO success(Object object, int status, String message) {
        ResultVO resultvo = new ResultVO();
        resultvo.setData(object);
        resultvo.setCode(status);
        resultvo.setMsg(message);
        return resultvo;
    }


    /**
     * 返回数据,为空的时候
     * @return Result模型的数据
     */
    public static ResultVO success() {
        return success("");
    }

    /**
     * 能否做到根据状态码,查找相应的nessage返回呢.
     * @return
     */
    public static ResultVO error(int status, String message) {
        ResultVO resultvo = new ResultVO();
        resultvo.setCode(status);
        resultvo.setMsg(message);
        return resultvo;
    }

    /**
     * 系统
     * @param resultEnum
     * @return
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

    /**
     * 能否做到根据状态码,查找相应的nessage返回呢.
     * @return
     */
    public static ResultVO error(int status) {
        ResultVO resultvo = new ResultVO();
        resultvo.setCode(status);
        String message = new String();
        message = statusToMessage(status);
        resultvo.setMsg(message);
        return resultvo;
    }

    /**
     * 把这个做成一个常用的工具类.对于状态码和提示语,也单独做个文件.读取这个文件,写入到hashMap里面去.
     * @param status
     * @return
     */
    public static String statusToMessage(int status) {
        String msg = ResultEnum.getMessage(status);
        return msg;
    }

}
