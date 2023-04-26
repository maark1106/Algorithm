#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1 ,-1 ,0,0 };

int main(void)
{
    int map[52][52] = { 0 };

    int N, res = 0, i, j, k;
    cin >> N;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++)
            cin >> map[i][j];
    }

    for (i = 1; i <= N; i++) {
        for (j = 1; j <= N; j++) {
            for (k = 0; k < 4; k++) {
                if (map[i + dx[k]][j + dy[k]] >= map[i][j])
                    break;
            }
            if (k == 4) {               
                res++;
            }
        }
    }

    cout << res;

    return 0;
}
