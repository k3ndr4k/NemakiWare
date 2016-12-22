package util.authentication;

import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;

import play.Logger;
import play.Logger.ALogger;
import play.i18n.Messages;
import util.Util;

public class NemakiAuthenticator implements Authenticator<UsernamePasswordCredentials>{
	private static final ALogger logger = Logger.of(NemakiAuthenticator.class);

	@Override
	public void validate(UsernamePasswordCredentials credentials, WebContext context) throws HttpAction {
		final String repositoryId = context.getRequestParameter("repositoryId");
		final String userId = credentials.getUsername();
		final String password = credentials.getPassword();

		try{
			Util.createCmisSessionByBasicAuth(repositoryId, userId, password);
		}catch(CmisUnauthorizedException e){
			throwsException(Messages.get("view.auth.login.error"));
		}catch(CmisConnectionException e){
			throwsException(Messages.get("view.auth.login.error.connection"));
		}
	}

	protected void throwsException(String message) {
		throw new CredentialsException(message);// 47
	}
}
