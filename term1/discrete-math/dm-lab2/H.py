m = int(input())
sum = 0
a = list(map(int, input().split()))
for i in range(m):
    sum += a[i]
s = input()
A = 0
n = len(s)
for i in range(n):
    A += int(s[i]) * 2**(n - i - 1)
q = 2**len(s)
l = 0
L = [0] * m
R = [0] * m
c = ["0"] * m
for i in range(m):
    L[i] = l
    R[i] = l + a[i]
    c[i] = chr(i + 97)
    l = R[i]
ans = ""
B = 1
for i in range(sum):
    for j in range(m):
        if A * sum >= L[j] * q * B and A * sum < R[j] * q * B:
            ans = ans + c[j]
            A = A * sum - L[j] * q * B
            B = B * (R[j] - L[j])
            break
print(ans)
