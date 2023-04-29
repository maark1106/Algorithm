#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(void)
{
    int arr[9][9];
    int avg[9] = { 0 };

    for (int i = 0; i < 9; i++) {
        int sum = 0;
        for (int j = 0; j < 9; j++) {    
            cin >> arr[i][j];
            sum += arr[i][j];
        }
        avg[i] = (sum / 9.0) + 0.5;
    }

    for (int i = 0; i < 9; i++) {
        int temp = abs(arr[i][0] - avg[i]);
        int res = arr[i][0];
        for (int j = 1; j < 9; j++) {
            int temp1 = abs(avg[i] - arr[i][j]);
            if (temp1 < temp) {
                temp = temp1;
                res = arr[i][j];
            }
            else if (temp1 == temp) {
                temp = temp1;
                if (res < arr[i][j])
                    res = arr[i][j];
            }
        }
        cout << avg[i] << " " << res << endl;
    }


    return 0;
}
