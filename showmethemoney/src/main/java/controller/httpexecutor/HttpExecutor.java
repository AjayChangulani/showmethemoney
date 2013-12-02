package controller.httpexecutor;

import java.net.URL;

import controller.dto.Location;

public interface HttpExecutor
{
	Location getLocation(URL url);
}
