package com.cloudneer.pw;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;

import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;

public class App
{
    private AWSCredentialsProvider credentials = InstanceProfileCredentialsProvider.getInstance();
    private AWSSimpleSystemsManagement simpleSystemsManagementClient = AWSSimpleSystemsManagementClientBuilder.standard().withCredentials(credentials).withRegion("ap-southeast-2").build();

    public static void main( String[] args )
    {
        String password;
        String paramkey;

        paramkey = args[0];

        App MyApp = new App();
        password = MyApp.getParameterFromSSMByName( paramkey );
        System.out.println( password );
    }


    protected String getParameterFromSSMByName(String parameterKey) {
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(parameterKey).setWithDecryption(Boolean.valueOf(true));
        GetParameterResult parameterResult = simpleSystemsManagementClient.getParameter(parameterRequest);
        return parameterResult.getParameter().getValue();

    }


}
