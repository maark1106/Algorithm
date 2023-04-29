#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int dx[10];
int dy[10];

int main(void)
{    
    int N;
    cin >> N;
    for (int i = 0; i < N; i++)
        cin >> dx[i];
    for (int i = 0; i < N; i++)
        cin >> dy[i];

    int res = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            res += dx[i] < dy[j] ? dx[i] : dy[j];
        }
    }

    cout << res;

    return 0;
}
