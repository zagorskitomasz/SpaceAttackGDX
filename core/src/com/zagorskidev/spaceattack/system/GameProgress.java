package com.zagorskidev.spaceattack.system;

public class GameProgress
{
	private Integer mission;
	private Integer level;
	private Long experience;

	public GameProgress()
	{
		mission = 1;
		level = 1;
		experience = 0l;
	}

	public Integer getMission()
	{
		return mission;
	}

	public void setMission(Integer mission)
	{
		this.mission = mission;
	}

	public Integer getLevel()
	{
		return level;
	}

	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Long getExperience()
	{
		return experience;
	}

	public void setExperience(Long experience)
	{
		this.experience = experience;
	}
}
