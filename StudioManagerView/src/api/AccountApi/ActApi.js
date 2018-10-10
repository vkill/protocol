import axios from 'axios';


export async function getTotalAndTodayCount() {
  return axios({
    url: '/api/actmgr/getCount',
    method: 'get',
  });
}
