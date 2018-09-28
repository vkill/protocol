import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

const helper_url = '/api/dy_helper';

/**
 * 获取videoId
 * @param {*} shareUrl 
 */
export const toVideoId = (shareUrl) => axios.post(`${helper_url}/toVideoId`, {
    share_url: shareUrl
});