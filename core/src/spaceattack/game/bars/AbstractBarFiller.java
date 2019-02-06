package spaceattack.game.bars;

import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.system.notifiers.ValueObserver;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.Rect;

public abstract class AbstractBarFiller
{
	private IPool pool;

	private float amount;
	private float maxAmount;
	protected float percent;

	protected ILabel label;

	protected Rect rect;

	public AbstractBarFiller(IPool pool,IUtils utils)
	{
		this.pool = pool;

		IObserver<Float> observer = new ValueObserver(this::valueChanged);
		pool.registerObserver(observer);

		label = utils.createBarLabel();
		rect = new Rect();
	}

	public void valueChanged(float percent)
	{
		amount = pool.getAmount();
		maxAmount = pool.getMaxAmount();
		this.percent = percent;

		if (label != null)
			label.setText(String.format("%.0f", amount) + " / " + String.format("%.0f", maxAmount));
	}

	public abstract void drawRect(IBatch batch);

	public void drawLabel(IBatch batch)
	{
		if (label != null)
			label.draw(batch, 0.8f);
	}

	public float getMaxAmount()
	{
		return maxAmount;
	}

	public float getAmount()
	{
		return amount;
	}

	public float getPercent()
	{
		return percent;
	}
}
