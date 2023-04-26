#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(void)
{
    int N, second, sum = 0;
    cin >> N;
    vector<int> arr(N);

    for (int i = 0; i < N; i++) {
        cin >> arr[i];
        sum += arr[i];
    }        
    
    cin >> second;

    if (sum <= second) {
        cout << -1;
        return 0;
    }

    int cnt = 0, idx = 0;
    while (1) {
        idx++;
        if (idx >= N)
            idx = 0;
        if (arr[idx] == 0)
            continue;
        arr[idx]--;
        cnt++;
        if (cnt == second)
            break;
    }
    while (1) {
        idx++;
        if (idx >= N)
            idx = 0;
        if (arr[idx] != 0)
            break;
    }
    
    cout << idx + 1;
    
    

    return 0;
}
