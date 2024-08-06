package tech.intellispaces.actions.onetime;

import tech.intellispaces.actions.Action5;

import java.util.function.Function;

class NotFirstTimeOnlyAction<V, D1, D2, D3, D4, D5> implements Action5<V, D1, D2, D3, D4, D5> {
  private final Action5<V, D1, D2, D3, D4, D5> action;
  private boolean first = true;

  NotFirstTimeOnlyAction(Action5<V, D1, D2, D3, D4, D5> action) {
    this.action = action;
  }

  @Override
  public V execute(D1 data1, D2 data2, D3 data3, D4 data4, D5 data5) {
    V result = null;
    if (!first) {
      result = action.execute(data1, data2, data3, data4, data5);
    }
    first = false;
    return result;
  }

  @Override
  public <A extends Action5<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action5<V, D1, D2, D3, D4, D5>, A> wrapperFactory
  ) {
    return wrapperFactory.apply(this);
  }
}
