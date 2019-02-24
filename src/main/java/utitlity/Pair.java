package utitlity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class Pair<U, V> {
    public final U first;
    public final V second;
}
