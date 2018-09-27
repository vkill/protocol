import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

const web_order_type_url = '/api/orderType'

const web_order_url = '/api/order'

/**
 * 获取项目类型
 */
export const get_pro_type = () => axios.get(`${web_order_type_url}/getType`);


/**
 * 获取项目的货物
 * @param {类型标记} type_sign 
 */
export const get_goods_list_by_type = (type_sign) => axios.post(`${web_order_type_url}/goods`,{
    type_sign: type_sign,
});

/**
 * 
 * @param {pro_type:'dy', goods:'dydz100', videoid:'abc',order_count:10} postData 
 */
export const make_order = (postData) => axios.post(`${web_order_url}/make_web_order`, {
    pro_type:postData.pro_type,
    goods: postData.goods,
    video_id: postData.videoid,
    order_count: postData.order_count
});