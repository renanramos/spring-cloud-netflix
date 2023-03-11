package br.com.renanrramossi.auth.interfaceadapter.gateway;

import br.com.renanrramossi.auth.core.usecase.gateway.PermissionGateway;
import br.com.renanrramossi.auth.interfaceadapter.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PermissionGatewayImpl implements PermissionGateway {

  private final PermissionRepository permissionRepository;
}
